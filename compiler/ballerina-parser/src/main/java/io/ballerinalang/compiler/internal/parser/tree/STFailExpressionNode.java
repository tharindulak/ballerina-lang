/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerinalang.compiler.internal.parser.tree;

import io.ballerinalang.compiler.syntax.tree.FailExpressionNode;
import io.ballerinalang.compiler.syntax.tree.Node;
import io.ballerinalang.compiler.syntax.tree.NonTerminalNode;
import io.ballerinalang.compiler.syntax.tree.SyntaxKind;

import java.util.Collection;
import java.util.Collections;

/**
 * This is a generated internal syntax tree node.
 *
 * @since Swan Lake
 */
public class STFailExpressionNode extends STExpressionNode {
    public final STNode failKeyword;
    public final STNode expression;

    STFailExpressionNode(
            SyntaxKind kind,
            STNode failKeyword,
            STNode expression) {
        this(
                kind,
                failKeyword,
                expression,
                Collections.emptyList());
    }

    STFailExpressionNode(
            SyntaxKind kind,
            STNode failKeyword,
            STNode expression,
            Collection<STNodeDiagnostic> diagnostics) {
        super(kind, diagnostics);
        this.failKeyword = failKeyword;
        this.expression = expression;

        addChildren(
                failKeyword,
                expression);
    }

    public STNode modifyWith(Collection<STNodeDiagnostic> diagnostics) {
        return new STFailExpressionNode(
                this.kind,
                this.failKeyword,
                this.expression,
                diagnostics);
    }

    public STFailExpressionNode modify(
            SyntaxKind kind,
            STNode failKeyword,
            STNode expression) {
        if (checkForReferenceEquality(
                failKeyword,
                expression)) {
            return this;
        }

        return new STFailExpressionNode(
                kind,
                failKeyword,
                expression,
                diagnostics);
    }

    public Node createFacade(int position, NonTerminalNode parent) {
        return new FailExpressionNode(this, position, parent);
    }

    @Override
    public void accept(STNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(STNodeTransformer<T> transformer) {
        return transformer.transform(this);
    }
}
