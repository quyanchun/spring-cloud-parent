package com.yanchun.common.sourcegenerator.impl;



import com.yanchun.common.sourcegenerator.AbstractGenerator;
import com.yanchun.common.sourcegenerator.ContentsFilter;
import com.yanchun.common.sourcegenerator.utils.GeneratorConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ServiceImplGenerator extends AbstractGenerator {

    // 模板路径
    private static final String templatePath = "/codetemplate/ServiceImpl.template";

    private ContentsFilter filter;

    private final String templateContents;

    public ServiceImplGenerator(final GeneratorConfiguration config) {
        super(config, "serviceImpl");
        this.initFilter();
        this.templateContents = this.getFileString(ServiceImplGenerator.templatePath);
    }

    @Override
    public void generate() {
        final String value = this.filter.filter(this.templateContents);
        this.output(value);
    }

    private void initFilter() {
        final String servicePackage = this.getPackage("service") + ".impl";

        final Map<String, String> filterMap = new HashMap<String, String>();
        filterMap.put("@Package@", servicePackage);
        filterMap.put("@ServicePath@", this.getClassPath("service"));
        filterMap.put("@ModelPath@", this.getModelPath());
        filterMap.put("@Model@", this.getModelName());
        filterMap.put("@model@", this.getModelNameWithHeadLow());
        filterMap.put("@RepositoryPath@", this.getClassPath("repository"));
        this.filter = new ReplaceFilter(filterMap);
    }
}
