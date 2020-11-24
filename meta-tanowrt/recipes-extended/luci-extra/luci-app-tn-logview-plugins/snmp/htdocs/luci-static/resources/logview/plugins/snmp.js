'use strict';

return L.Class.extend({
	title: _('SNMP'),
	description: '',
	order: 10,
	acl: 'luci-app-tn-logview-snmpd',
	json_data: {
		add_to_downloads: true,
		action: {
			command: '/usr/libexec/luci-logview/logview-snmpd',
			command_args: ['json' ]
		}
	},
	downloads: {
		'raw': {
			display: 'RAW',
			mime_type: 'text/plain',
			extension: 'txt',
			action: {
				command: '/usr/libexec/luci-logview/logview-snmpd',
				command_args: [ 'plain' ]
			}
		}
	},
	columns: [
		{ name: 'message', display: _('Message') }
	]
});
