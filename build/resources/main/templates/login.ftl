<#include "layout.ftl">
<#macro page_head>
    <title>Admin</title>
</#macro>

<#macro page_login_form>
    <div class="col-lg-9">
        <form method="post" action="/admin/auth">
            <div class="form-group">
                <label for="exampleInputEmail1">Email</label>
                <input name="email"  type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="...">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="...">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</#macro>

<@display_page/>