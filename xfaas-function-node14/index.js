const express = require("express");
const handler = require("./handler/handler")

const app = express();
const port = 8080;


app.get("/", handler.function);

app.listen(port, function () {
    console.log(`XFaaS Function is listening on port ${port}!`);
});
