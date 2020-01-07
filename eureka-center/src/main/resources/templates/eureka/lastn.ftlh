<#import "/spring.ftl" as spring />
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <base href="<@spring.url basePath/>">
    <title>Eureka - Last N events</title>
    <link rel="stylesheet" type="text/css" href="eureka/css/wro.css">
</head>
<body id="three">

<!--[if lt IE 7]>
<p>你正在使用 <strong>过时的浏览器（如IE7等）</strong> 浏览器。请升级您的浏览器以改善您的体验。</p>
<![endif]-->

<#include "header.ftlh">

<div class="container-fluid xd-container">
    <#include "navbar.ftlh">

    <div id="xd-jobs" class="tab-pane active col-md-12">
        <ul class="nav nav-tabs" role="tablist" id="myTab">
            <li class="active"><a data-toggle="tab" href="#cancelled">Last 1000 cancelled leases</a></li>
            <li><a data-toggle="tab" href="#registered">Last 1000 newly registered leases</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane" id="cancelled">
                <table id='lastNCanceled' class="table table-striped table-hover">
                    <thead>
                    <tr><th>Timestamp</th><th>Lease</th></tr>
                    </thead>
                    <tbody>
                    <#if lastNCanceled?has_content>
                        <#list lastNCanceled as entry>
                            <tr><td>${entry.date?datetime}</td><td>${entry.id}</td></tr>
                        </#list>
                    <#else>
                        <tr><td colspan="2">No results available</td></tr>
                    </#if>
                    <tbody>
                </table>
            </div>
            <div class="tab-pane" id="registered">
                <table id='lastNRegistered' class="table table-striped table-hover">
                    <thead>
                    <tr><th>Timestamp</th><th>Lease</th></tr>
                    </thead>
                    <tbody>
                    <#if lastNRegistered?has_content>
                        <#list lastNRegistered as entry>
                            <tr><td>${entry.date?datetime}</td><td>${entry.id}</td></tr>
                        </#list>
                    <#else>
                        <tr><td colspan="2">No results available</td></tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="eureka/js/wro.js" ></script>
<script type="text/javascript">
    $(function () {
        $('#myTab a:last').tab('show')
    })
</script>
</body>
</html>