
## gFaaS Core Node14

Package contains functionality to create a function.

### Publish

```
npm publish
```

### Installation

```
npm install gfaas-core-node14
```


### Adapter

Create a file named <code>function.js</code> and add the following code. 
The handler can be extended with your custom code. The <code>Dockerfile</code> can be used for packaging.

<pre>
const gfaas = require("gfaas-core-node14")

/**
 * Responds to any HTTP request.
 *
 *  See https://expressjs.com/de/api.html for more information
 */
function handler(req, res) {
    // Put here your custom code
    res.send("Hello World!")
}

gfaas.runApp(handler)
</pre>
