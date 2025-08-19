setTimeout(function(){
	Java.perform(function (){
	console.log("[*] Script loaded")

	var MainActivity = Java.use("org.owasp.mstg.antifrida.MainActivity")

	MainActivity.checkMemory.overload().implementation = function() {
		console.log("[*] checkMemory function invoked")
		return false
	}

	});

});