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

package org.ballerinalang.test.query;

import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BMap;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.test.util.BCompileUtil;
import org.ballerinalang.test.util.BRunUtil;
import org.ballerinalang.test.util.CompileResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This contains methods to test simple query expression with from, where and select clauses
 */
public class SimpleQueryExpressionWithDefinedTypeTest {

    private CompileResult result;

    @BeforeClass
    public void setup() {
        result = BCompileUtil.compile("test-src/query/simple-query-with-defined-type.bal");
    }

    @Test(description = "Test simple select clause - simple variable definition statement ")
    public void testSimpleSelectQueryWithSimpleVariable() {
        BValue[] returnValues = BRunUtil.invoke(result, "testSimpleSelectQueryWithSimpleVariable");
        Assert.assertNotNull(returnValues);

        Assert.assertEquals(returnValues.length, 3, "Expected events are not received");

        BMap<String, BValue> person1 = (BMap<String, BValue>) returnValues[0];
        BMap<String, BValue> person2 = (BMap<String, BValue>) returnValues[1];
        BMap<String, BValue> person3 = (BMap<String, BValue>) returnValues[2];

        Assert.assertEquals(person1.get("firstName").stringValue(), "Alex");
        Assert.assertEquals(person2.get("lastName").stringValue(), "Fonseka");
        Assert.assertEquals(((BInteger) person3.get("age")).intValue(), 33);
    }

    @Test(description = "Test simple select clause - record variable definition statement ")
    public void testSimpleSelectQueryWithRecordVariable() {
        BValue[] returnValues = BRunUtil.invoke(result, "testSimpleSelectQueryWithRecordVariable");
        Assert.assertNotNull(returnValues);

        Assert.assertEquals(returnValues.length, 3, "Expected events are not received");

        BMap<String, BValue> person1 = (BMap<String, BValue>) returnValues[0];
        BMap<String, BValue> person2 = (BMap<String, BValue>) returnValues[1];
        BMap<String, BValue> person3 = (BMap<String, BValue>) returnValues[2];

        Assert.assertEquals(person1.get("firstName").stringValue(), "Alex");
        Assert.assertEquals(person2.get("lastName").stringValue(), "Fonseka");
        Assert.assertEquals(((BInteger) person3.get("age")).intValue(), 33);
    }

    @Test(description = "Test simple select clause - record variable definition statement v2 ")
    public void testSimpleSelectQueryWithRecordVariableV2() {
        BValue[] returnValues = BRunUtil.invoke(result, "testSimpleSelectQueryWithRecordVariableV2");
        Assert.assertNotNull(returnValues);

        Assert.assertEquals(returnValues.length, 3, "Expected events are not received");

        BMap<String, BValue> person1 = (BMap<String, BValue>) returnValues[0];
        BMap<String, BValue> person2 = (BMap<String, BValue>) returnValues[1];
        BMap<String, BValue> person3 = (BMap<String, BValue>) returnValues[2];

        Assert.assertEquals(person1.get("firstName").stringValue(), "Alex");
        Assert.assertEquals(person2.get("lastName").stringValue(), "Fonseka");
        Assert.assertEquals(((BInteger) person3.get("age")).intValue(), 33);
    }

    @Test(description = "Test simple select clause with a where clause")
    public void testSimpleSelectQueryWithWhereClause() {
        BValue[] returnValues = BRunUtil.invoke(result, "testSimpleSelectQueryWithWhereClause");
        Assert.assertNotNull(returnValues);

        Assert.assertEquals(returnValues.length, 2, "Expected events are not received");

        BMap<String, BValue> person1 = (BMap<String, BValue>) returnValues[0];
        BMap<String, BValue> person2 = (BMap<String, BValue>) returnValues[1];

        Assert.assertEquals(person1.get("firstName").stringValue(), "Ranjan");
        Assert.assertEquals(person2.get("firstName").stringValue(), "John");
        Assert.assertEquals(((BInteger) person1.get("age")).intValue(), 30);
        Assert.assertEquals(((BInteger) person2.get("age")).intValue(), 33);
    }
}