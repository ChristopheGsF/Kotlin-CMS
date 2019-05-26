<#include "layout.ftl">
<#macro page_head>
    <title>Article</title>
</#macro>

<#macro page_body>
    <div class="col-lg12 col-md-9 mb-12">
        <div class="card h-100">
            <div class="card-header">
                <h4 class="card-title"> ${article.title}</h>
            </div>
            <div class="card-body">
                <p class="card-text">${article.text}</p>
            </div>
            <div class="card-footer">
                <form action="/comment/add" method="post">
                    <input type="hidden" name="article_id" value="${article.id}">
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Username</label>
                        <input name="username" type="text" class="form-control" id="exampleFormControlInput1" placeholder="...">
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1">Comments</label>
                        <textarea name="text" class="form-control" id="exampleFormControlTextarea1" placeholder="..." rows="4"></textarea>
                    </div>
                    <div class="form-group">
                        <button type="submit" style="width: 100%;" class="btn btn-success">Send</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</#macro>

<#macro page_list_comment>
    <div class="row" style="margin-top: 50px; width: 100%;">
        <div class="col-lg-12" style="display: contents;" >
            <#list article.comment as comment>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card">
                        <div class="card-header">
                            ${comment.username}
                            <footer class="blockquote-footer"><cite title="Source Title">${comment.date}</cite></footer>
                        </div>
                        <div class="card-body">
                            <blockquote class="blockquote mb-0">
                                <p>${comment.text}</p>

                            </blockquote>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>


<@display_page/>