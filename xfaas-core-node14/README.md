
## XFaaS Core Node14

Package contains functionality to create a function.

### Publish

- <code>npm publish</code>

### Installation

- <code>npm install xfaas-core-node14</code>


### Adapter

Create a file named <code>function.js</code> and add the following code. 
The handler can be extended with your custom code. The <code>Dockerfile</code> can be used for packaging.

<pre>
const xfaas = require("xfaas-core-node14")

/**
 * Responds to any HTTP request.
 *
 *  See https://expressjs.com/de/api.html for more information
 */
function handler(req, res) {
    // Put here your custom code
    res.send("Hello World!")
}

xfaas.runApp(handler)
</pre>
