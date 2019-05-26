<#include "layout.ftl">
<#macro page_head>
    <title>List Article</title>
</#macro>

<#macro page_body>

    <div class="container col-lg-9">
        <!-- /.col-lg-3 -->
            <div class="row">
                <#assign count = 0/>
                <#list list as article>

                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <div class="card-header">
                                <h4 class="card-title">
                                    <#if article.title?length &lt; 20>
                                        <a href="#">${article.title}</a>
                                    <#else>
                                        <a href="#">${article.title?substring(0,20)} ...</a>

                                    </#if>
                                </h4>
                            </div>
                            <div class="card-body">
                                <#if article.text?length &lt; 150>
                                    <p class="card-text">${article.text} </p>
                                <#else>
                                    <p class="card-text">${article.text?substring(0,150)} ...</p>
                                </#if>
                            </div>
                            <div class="card-footer">
                                <a href="/article/${article.id}" class="btn btn-primary">More</a>
                            </div>
                        </div>
                    </div>
                    <#assign count++ />
                </#list>

            </div>
            <!-- /.row -->

    </div>
    <!-- /.row -->

    </div>
    <!-- /.container -->

</#macro>

<@display_page/>