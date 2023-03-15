const express = require("express");
const handler = require("./handler/handler")
const info = require("./info/info")

const app = express();
const port = 8080;


app.get("/_/health", info.health);
app.get("/_/ready", info.ready);
app.get("/", handler.function);
app.post("/", handler.function);

app.listen(port, function () {
    console.log(`XFaaS Function is listening on port ${port}`);
});
