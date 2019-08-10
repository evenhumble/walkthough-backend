import dashboardGenerator as dg
import sys
import json

app_name = sys.argv[1]
env = sys.argv[2]

generator = dg.DashboardGenerator('honeybee', app_name, env)

print json.dumps(generator.dashboard([
    generator.row(
        [
            generator.panel("uptime", 's', [generator.metricForAppInEnv('aliasByNode(stats.gauges.services.$APP$.$ENV$.uptime.*,6)')]),
            generator.panel("Heap", 'none', [generator.metricForAppInEnv('aliasByNode(stats.gauges.services.$APP$.$ENV$.heapMemory.max.*, 7)'),
                                             generator.metricForAppInEnv('aliasByNode(stats.gauges.services.$APP$.$ENV$.heapMemory.used.*, 7)')]),
            generator.panel("NonHeap", 'none', [generator.metricForAppInEnv('aliasByNode(stats.gauges.services.$APP$.$ENV$.nonHeapMemory.max.*, 7)')])
        ]),
    generator.row(
        [
            generator.panel("Request rate (by response code)", 's', [generator.metricForAppInEnv("aliasByNode(hitcount(stats.services.$APP$.$ENV$.requests.*.*, '10s'), 5, 6)")]),
            generator.panel("Response latency", 's', [generator.metricForAppInEnv("aliasByNode(stats.timers.services.$APP$.$ENV$.latency.*.mean, 6)")])
        ])
]))
