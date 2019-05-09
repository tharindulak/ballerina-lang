/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.docgen;


import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import org.ballerinalang.docgen.docs.BallerinaDocConstants;
import org.ballerinalang.docgen.docs.BallerinaDocDataHolder;
import org.ballerinalang.docgen.generator.model.DefaultableVarible;
import org.ballerinalang.docgen.generator.model.PageContext;
import org.ballerinalang.docgen.generator.model.Type;
import org.ballerinalang.docgen.generator.model.UnionType;
import org.ballerinalang.docgen.generator.model.Variable;
import org.ballerinalang.docgen.model.ModuleDoc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates the HTML pages from the Page objects.
 */
public class Writer {
    private static PrintStream out = System.out;

    /**
     * Write the HTML document from the Page object for a bal package.
     *
     * @param object              Page object which is generated from the bal package.
     * @param packageTemplateName hbs template file to be used.
     * @param filePath            path of the file to write the output.
     * @throws IOException on an IO error.
     */
    public static void writeHtmlDocument(Object object, String packageTemplateName, String filePath) throws
            IOException {
        String templatesFolderPath = System.getProperty(BallerinaDocConstants.TEMPLATES_FOLDER_PATH_KEY, File
                .separator + "template" + File.separator + "html");

        String templatesClassPath = System.getProperty(BallerinaDocConstants.TEMPLATES_FOLDER_PATH_KEY,
                "/template/html");
        PrintWriter writer = null;
        try {
            Handlebars handlebars = new Handlebars().with(new ClassPathTemplateLoader(templatesClassPath), new
                    FileTemplateLoader(templatesFolderPath));
            handlebars.registerHelpers(StringHelpers.class);
            handlebars.registerHelper("paramSummary", (Helper<List<DefaultableVarible>>)
                    (varList, options) -> varList.stream()
                            .map(variable -> getTypeLabel(variable.type, options) + " " + variable.name)
                            .collect(Collectors.joining(", "))
            );
            handlebars.registerHelper("returnParamSummary", (Helper<List<Variable>>)
                    (varList, options) -> varList.stream()
                            .map(variable -> getTypeLabel(variable.type, options) + " " + variable.name)
                            .collect(Collectors.joining(", "))
            );
            handlebars.registerHelper("unionTypeSummary", (Helper<List<Type>>)
                    (typeList, options) -> typeList.stream()
                            .map(type -> getTypeLabel(type, options))
                            .collect(Collectors.joining(" | "))
            );
            handlebars.registerHelper("typeName", Writer::getTypeLabel);
            Template template = handlebars.compile(packageTemplateName);

            writer = new PrintWriter(filePath, "UTF-8");

            Context context = Context.newBuilder(object).resolver(FieldValueResolver.INSTANCE).build();
            writer.println(template.apply(context));
            out.println("docerina: HTML file written: " + filePath);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static String getTypeLabel(Type type, Options options) {
        String root = getRootPath(options);
        return type.isAnonymousUnionType
                ? type.memberTypes.stream()
                    .map(type1 -> getHtmlLink(type1, root))
                    .collect(Collectors.joining(" | "))
                : getHtmlLink(type, root);
    }

    private static String getRootPath(Options options) {
        return getNearestPageContext(options.context).rootPath;
    }

    private static PageContext getNearestPageContext(Context context) {
        return context.model() instanceof PageContext
                ? (PageContext) context.model()
                : getNearestPageContext(context.parent());
    }

    private static String getHtmlLink(Type type, String root) {
        String orgName = BallerinaDocDataHolder.getInstance().getOrgName();
        Map<String, ModuleDoc> packageMap = BallerinaDocDataHolder.getInstance().getPackageMap();
        String link = root + type.moduleName + "/" + type.category + "/" + type.name + ".html";;
        // If this is a local module, generate relative link to the type
        if (orgName.equals(type.orgName) && packageMap.containsKey(type.moduleName)) {
            // TODO fix the orgName equals
        }

        return "<a href=\"" + link + "\">" + type.name + "</a>";
    }
}
