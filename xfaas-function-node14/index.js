const xfaas = require("xfaas-core-node14")

/**
 * Responds to any HTTP request.
 *
 *  See https://expressjs.com/de/api.html for more information
 */
function handler(req, res) {
    res.send("Hello World!")
}

xfaas.runApp(handler)

