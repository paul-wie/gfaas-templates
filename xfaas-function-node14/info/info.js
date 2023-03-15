/*
    Health and Readiness endpoints for OpenFaaS
 */
exports.health = (req, res) => {
    res.send("Ok")
};

exports.ready = (req, res) => {
    res.send("Ok")
};
