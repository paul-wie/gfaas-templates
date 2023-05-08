package org.gfaas.core.runner;

import org.gfaas.core.model.XFunction;

import java.util.Arrays;
import java.util.stream.Collectors;

public class XRunner {

    public static void main(String[] args) throws Exception {

        var map = Arrays.stream(args)
                .map(s -> s.trim().split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        var functionTarget = map.get("--functionTarget");

        if(functionTarget == null || functionTarget.isEmpty()){
            System.out.println("Argument functionTarget is required but was not provided.");
            return;
        }

        XFunction xFunction = (XFunction) Class.forName(functionTarget).getDeclaredConstructor().newInstance();

        var xServer = new XServer();
        xServer.startServer(xFunction);

    }

}
