
class ThreadInfo
	attr_accessor :stack, :name, :locks, :to_lock, :tid, :runnable

	def stack()
		@stack
	end

	def stack=(stack)
		@stack = stack
	end

	def initialize(id, name)

		@tid = id
		@name = name
		@runnable = false
		@locks= []
		@to_lock= nil
	end
end


### Main
filename = ARGV.shift

if filename == nil || ! File.readable?(filename) then
	fail( "Usage: $0 <thread_dump_file>")
end

# Parse thread dump file
lock_to_thread = {}
threads = []
dumps = []
timestamp = /\[\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3} [A-Z]{3}\]: /
index = []

File.open(filename) do |file|
	processing = false
	count = 0
	currentThread = nil
	line_no = 0
	file.each_line do |line|
		# skip heading lines that are not part of the Thread dump
		# also used to find new dumps in a singular file
		line_no += 1
		processing ||= ( line =~ /Full thread dump/ )
		next if not processing

		case line
			when /"(.*)".*tid=(\w+) +nid=\w+ (runnable)?/
				currentThread = ThreadInfo.new($2, $1)
				currentThread.runnable = ($3 != nil)
				threads << currentThread

			when /locked <(\w+)>/
				if currentThread != nil then
					currentThread.locks << $1
					lock_to_thread[$1] = currentThread.tid
				end
			when /waiting to lock <(\w+)>/
				currentThread.to_lock = $1 if currentThread != nil

			when /^#{timestamp}Heap/
				count += 1
				processing = false
				dumps << threads
				threads = []
				index << line_no
				puts "parsed Dump number #{count}. ended at line: #{line_no}"

		end
	end
end

 no = 1
 dumps.each do |threads|
	File.open("#{filename}.#{no}.dot", 'w') do |out|

		## Generate output
		out.puts 'digraph G {
		edge [fontname="Helvetica",fontsize=10,labelfontname="Helvetica",labelfontsize=10];
		node [fontname="Helvetica",fontsize=10,shape=record, style=filled];
		rankdir=LR;
		ranksep=1;
		bgcolor=white;'


		threads.each do |t|
			# we ignore threads that are not involved in lockings
			next if t.locks.empty? and t.to_lock == nil

			color = if t.runnable then "green" else t.to_lock == nil ? "pink" : "red" end

			out.puts "Thread#{t.tid} [label=\"#{t.name}|Owns: #{t.locks.join(",") }\" fillcolor=#{color} ];"

			# print the Lock dependencies between threads (edges of the graph)
			if t.to_lock != nil then
				lockOwner = lock_to_thread[t.to_lock] || "Unknown"
				out.puts "Thread#{t.tid} -> Thread#{lockOwner} [taillabel=\"#{t.to_lock}\" arrowhead=normal,arrowtail=none];"
			end
		end

		out.puts "}"
		no += 1
	end
end

#break up the source files
no = 1

if index.size > 1 then
	source = File.open(filename, "r")
	prev = 0
	index.each do |idx|
		count_lines = idx.to_i - prev
		File.open("#{filename}.#{no}.txt", 'w') do |out|
			count_lines.times do
				out.print source.readline
			end
		end
		prev = idx.to_i
		no += 1
	end
	source.close
end
