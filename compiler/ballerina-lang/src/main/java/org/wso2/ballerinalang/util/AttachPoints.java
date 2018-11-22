/*
*  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.ballerinalang.util;

import org.ballerinalang.model.elements.AttachPoint;

import java.util.Set;

/**
 * {@code AttachPoints} is a helper for annotation attache points.
 *
 * @since 0.974.0
 */
public class AttachPoints {
    public static final int SERVICE = 1;
    public static final int RESOURCE = SERVICE << 1;
    public static final int REMOTE = RESOURCE << 1;
    public static final int FUNCTION = REMOTE << 1;
    public static final int OBJECT = FUNCTION << 1;
    public static final int CLIENT = OBJECT << 1;
    public static final int TYPE = CLIENT << 1;
    public static final int LISTENER = TYPE << 1;
    public static final int PARAMETER = LISTENER << 1;
    public static final int ANNOTATION = PARAMETER << 1;
    public static final int CHANNEL = ANNOTATION << 1;

    public static int asMask(Set<AttachPoint> attachPoints) {
        int mask = 0;
        for (AttachPoint point : attachPoints) {
            switch (point) {
                case SERVICE:
                    mask |= SERVICE;
                    break;
                case RESOURCE:
                    mask |= RESOURCE;
                case REMOTE:
                    mask |= REMOTE;
                    break;
                case FUNCTION:
                    mask |= FUNCTION;
                    break;
                case OBJECT:
                    mask |= OBJECT;
                case CLIENT:
                    mask |= CLIENT;
                    break;
                case TYPE:
                    mask |= TYPE;
                    break;
                case LISTENER:
                    mask |= LISTENER;
                    break;
                case PARAMETER:
                    mask |= PARAMETER;
                    break;
                case ANNOTATION:
                    mask |= ANNOTATION;
                    break;
                case CHANNEL:
                    mask |= CHANNEL;
                    break;
            }
        }
        return mask;
    }
}
