/**
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
define(['lodash', 'log', 'event_channel', './abstract-source-gen-visitor', './service-definition-visitor',
        './function-definition-visitor', './package-definition-visitor', './import-declaration-visitor',
        './connector-definition-visitor'],
    function(_, log, EventChannel, AbstractSourceGenVisitor, ServiceDefinitionVisitor, FunctionDefinitionVisitor,
             PackageDefinitionVisitor, ImportDeclarationVisitor, ConnectorDefinitionVisitor) {

    var BallerinaASTRootVisitor = function() {
        AbstractSourceGenVisitor.call(this);
    };

    BallerinaASTRootVisitor.prototype = Object.create(AbstractSourceGenVisitor.prototype);
    BallerinaASTRootVisitor.prototype.constructor = BallerinaASTRootVisitor;

    BallerinaASTRootVisitor.prototype.canVisitBallerinaASTRoot = function(serviceDefinition){
        return true;
    };

    BallerinaASTRootVisitor.prototype.beginVisitBallerinaASTRoot = function(serviceDefinition){
        log.debug('Begin Visit BallerinaASTRoot');
    };

    BallerinaASTRootVisitor.prototype.visitBallerinaASTRoot = function(serviceDefinition){
        log.debug('Visit BallerinaASTRoot');
    };

    BallerinaASTRootVisitor.prototype.endVisitBallerinaASTRoot = function(serviceDefinition){
        log.debug('End Visit BallerinaASTRoot');
    };

    BallerinaASTRootVisitor.prototype.visitServiceDefinition = function(serviceDefinition){
        var serviceDefinitionVisitor = new ServiceDefinitionVisitor(this);
        serviceDefinition.accept(serviceDefinitionVisitor);
    };

    BallerinaASTRootVisitor.prototype.visitConnectorDefinition = function(connectorDefinition){
        var connectorDefinitionVisitor = new ConnectorDefinitionVisitor(this);
        connectorDefinition.accept(connectorDefinitionVisitor);
    };

    BallerinaASTRootVisitor.prototype.visitFunctionDefinition = function(functionDefinition){
        var functionDefinitionVisitor = new FunctionDefinitionVisitor(this);
        functionDefinition.accept(functionDefinitionVisitor);
    };

    BallerinaASTRootVisitor.prototype.visitPackageDefinition = function(packageDefinition){
        var packageDefinitionVisitor = new PackageDefinitionVisitor(this);
        packageDefinition.accept(packageDefinitionVisitor);
    };

    BallerinaASTRootVisitor.prototype.visitImportDeclaration = function(importDeclaration){
        var importDeclarationVisitor = new ImportDeclarationVisitor(this);
        importDeclaration.accept(importDeclarationVisitor);
    };

    return BallerinaASTRootVisitor;
});