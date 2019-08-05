import dashboardGenerator as dg

import sys
import json

app_name = sys.argv[1]
env = sys.argv[2]
host = sys.argv[3]

print json.dumps(dg.KibanaDashboardGenerator('honeybee', app_name, env, 'access').dashboard(dg.KibanaFilter().with_field('hostname', host), ['time', 'verb', 'path', 'http_code', 'response_time', 'referer', 'user_agent', 'x_forwarded_for', 'size']))
