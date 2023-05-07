const PROTO_PATH = __dirname + '/function.proto';
const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');
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
const fun = protoDescriptor.org.proto;

const stub = new fun.Function('localhost:8080', grpc.credentials.createInsecure());
const functionRequest = {
    text: "Hello from Client"
}

stub.invoke(functionRequest, function (err, response) {
    console.log(response)
});
