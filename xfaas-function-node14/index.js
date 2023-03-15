const express = require("express");
const handler = require("./handler/handler")
const info = require("./info/info")

const app = express();
const port = 8080;

// Health and Readiness endpoints for OpenFaaS
app.get("/_/health", info.health);
app.get("/_/ready", info.ready);
// Health endpoint for Nuclio
app.get("/__internal/health", info.health);
// Handler function
app.get("/", handler.function);
app.post("/", handler.function);

app.listen(port, function () {
    console.log(`XFaaS Function is listening on port ${port}`);
});
