const express = require("express");
const handler = require("./handler/handler")
const info = require("./info/info")

/**
 * Responds to any HTTP request.
 *
 *  See https://expressjs.com/de/api.html for more information
 */
exports.runApp = function(targetFunction){
    const app = express();
    const nuclioHeatlh = express();
    const port = 8080;

    // Health and Readiness endpoints for OpenFaaS
    app.get("/_/health", info.health);
    app.get("/_/ready", info.ready);
    // Health endpoint for Nuclio
    app.get("/__internal/health", info.health);
    nuclioHeatlh.get("/live", info.health);
    // Handler function
    app.get("/", targetFunction);
    app.post("/", targetFunction);

    nuclioHeatlh.listen(8082, function () {
        console.log(`Health endpoint is listening on port 8082`);
    });

    app.listen(port, function () {
        console.log(`XFaaS Function is listening on port ${port}`);
    });
}
