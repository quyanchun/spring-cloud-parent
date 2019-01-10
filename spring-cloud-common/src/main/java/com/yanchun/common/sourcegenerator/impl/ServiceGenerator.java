package com.yanchun.common.sourcegenerator.impl;



import com.yanchun.common.sourcegenerator.AbstractGenerator;
import com.yanchun.common.sourcegenerator.ContentsFilter;
import com.yanchun.common.sourcegenerator.utils.GeneratorConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ServiceGenerator extends AbstractGenerator {

    // 模板路径
    private static final String templatePath = "/codetemplate/Service.template";

    private ContentsFilter filter;

    private final String templateContents;

    public ServiceGenerator(final GeneratorConfiguration config) {
        super(config, "service");
        this.initFilter();
        this.templateContents = this.getFileString(ServiceGenerator.templatePath);
    }

    @Override
    public void generate() {
        final String value = this.filter.filter(this.templateContents);
        this.output(value);
    }

    private void initFilter() {

        final Map<String, String> serviceFilterMap = new HashMap<String, String>();
        serviceFilterMap.put("@Package@", this.getPackage("service"));
        serviceFilterMap.put("@ModelPath@", this.getModelPath());
        serviceFilterMap.put("@Model@", this.getModelName());
        this.filter = new ReplaceFilter(serviceFilterMap);
    }
}
