require "time"
MAX_DEPTH = 2
TIME_FORMAT = "%T"

class ThreadInfo
	attr_accessor  :tid, :cpu, :mem

	def initialize(id, cpu, mem)
		@tid = id
		@cpu = cpu
		@mem = mem
	end
end

# this needs to be time sensitive

def get_dump_entry(dumpfile, nid, time)
	entry = "\nNEW ENTRY FOR nid=0x#{nid} at #{time.strftime(TIME_FORMAT)}\n"
	entry_part = false
	open dumpfile do |file|
		file.lines do |line|
			if entry_part then
				if line.include?("nid=") then
					entry_part = false
					break
				else
					entry << line.to_s
				end

			elsif line.include?("nid=0x#{nid}") && line.include?(time.strftime(TIME_FORMAT)) then
				entry_part = true
				entry << line.to_s
			end
		end
	end
	return entry
end


topfile = ARGV.shift
dumpfile = ARGV.shift

if topfile == nil || ! File.readable?(topfile) || dumpfile == nil || ! File.readable?(dumpfile) then
	fail( "Usage: $0 <output_from_top -H> $1 <thread_dump_file>")
end

java_threads = []

File.open(topfile) do |file|
	@time = Time.parse(/top - (\d\d:\d\d:\d\d) up/.match(file.readline)[1])
	puts @time.strftime(TIME_FORMAT)
	file.each_line do |line|
		if line.rstrip.end_with?("java") then
			parsed = /\s*(?<tid>\d+)\s*\w+\s*\d+\s*\d+\s*[A-Za-z0-9.]+\s*[A-Za-z0-9.]+\s*[A-Za-z0-9.]+\s*[A-Za-z0-9.]+\s*(?<cpu>[0-9.]+)\s*(?<mem>[0-9.]+).*/.match(line)
#			puts "TID, CPU, MEM = #{$1}(#{$1.to_i.to_s(16)}), #{$2}, #{$3}"
			java_threads << ThreadInfo.new($1.to_i.to_s(16), $2, $3)
		end
	end
end

# format is nid=somehex so get the whole entry

if java_threads.size > MAX_DEPTH then
	for i in 0..MAX_DEPTH do
		puts get_dump_entry(dumpfile, java_threads[i].tid, @time)
	end
else
	java_threads.each do |t|
		puts get_dump_entry(dumpfile, t.tid, @time)
	end
end


