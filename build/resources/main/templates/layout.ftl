<#macro page_head>
    <title>Layout</title>
</#macro>

<#macro page_body>
</#macro>

<#macro page_list_comment>
</#macro>

<#macro page_login_form>
</#macro>

<#macro display_page>
    <!doctype html>
    <html>
    <head>
        <@page_head/>
        <link rel="stylesheet" href="/static/css/reset.css">
        <link rel="stylesheet" href="/static/css/bootstrap.min.css">
        <link rel="stylesheet" href="/static/css/style.css">
    </head>
    <body>
    <div style="padding-top: 5vh" class="container">
        <div class="row">
            <div class="col-lg-3">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <h1 class="my-4"><a class="navbar-brand" href="/">Kotlin</a></h1>

                    <div class="list-group">
                        <a class="nav-item nav-link" href="/">Articles<span class="sr-only">(current)</span></a>
                        <#if auth >
                            <a class="nav-item nav-link" href="/logout">Logout</a>
                        <#else>
                            <a class="nav-item nav-link" href="/admin">Connection</a>
                        </#if>

                    </div>

            </div>
        <@page_body/>
        <@page_login_form/>

        </div>
    </div>

    <@page_list_comment/>
    </body>
    </html>
</#macro>