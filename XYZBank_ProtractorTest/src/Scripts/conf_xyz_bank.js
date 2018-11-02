var Jasmine2HtmlReporter = require('protractor-jasmine2-html-reporter');

exports.config = {

		directConnect : true ,	
		framework: 'jasmine',
		specs : ['roughSpec.js'],


		capabilities: {
			'browserName': 'chrome'
		},


		jasmineNodeOpts: {
			defaultTimeoutInterval: 150000
		},
		
		suites :
			{
				customer : ['/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/Scripts/customerFlows.js'],
				E2E : ['/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/Scripts/customerFlows.js','/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/Scripts/managerFlows.js']
			},

		onPrepare: function() {
			jasmine.getEnv().addReporter(
					new Jasmine2HtmlReporter({
						savePath: 'target/screenshots',
						takeScreenshots: true,
						takeScreenshotsOnlyOnFailures: true
					})
			);
		}
}
