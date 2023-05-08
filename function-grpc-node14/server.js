const PROTO_PATH = __dirname + '/function.proto';
const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');
const reflection  = require('grpc-node-server-reflection');

// Suggested options for similarity to existing grpc.load behavior
const packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });
const protoDescriptor = grpc.loadPackageDefinition(packageDefinition);
// The protoDescriptor object has the full package hierarchy
const fun = protoDescriptor.org.proto;

function invoke(request){
    console.log(request)
    return {
        text: "Hello from Server: " + request.text
    }
}

function getInvoke(call, callback) {
    callback(null, invoke(call.request));
}


function getServer() {
    const server = reflection.default(new grpc.Server());
    server.addService(fun.Function.service, {
        invoke: getInvoke,
    });
    return server;
}
const routeServer = getServer();
routeServer.bindAsync('0.0.0.0:8080', grpc.ServerCredentials.createInsecure(), () => {
    routeServer.start();
});
