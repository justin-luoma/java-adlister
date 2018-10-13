<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 10/6/18
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-expand-md fixed-top navbar-dark bg-dark" id="navbar">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a href="/" class="navbar-brand">Adlister</a>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="ads">Ads <span class="caret"></span></a>
                    <div class="dropdown-menu" aria-labelledby="ads">
                        <a class="dropdown-item not-logged-in" href="/profile/ads">My Listings</a>
                        <a class="dropdown-item not-logged-in" href="">Create New Listing</a>
                        <div class="dropdown-divider not-logged-in"></div>
                        <a class="dropdown-item" href="/ads">View All</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="">Furniture</a>
                        <a class="dropdown-item" href="">Video Games</a>
                        <a class="dropdown-item" href="">Appliances</a>

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Theme <span class="caret"></span></a>
                    <div class="dropdown-menu" aria-labelledby="themes">
                        <a class="dropdown-item" href="/theme?theme=default">Default</a>
                        <a class="dropdown-item" href="/theme?theme=cerulean">Cerulean</a>
                        <a class="dropdown-item" href="/theme?theme=cosmo">Cosmo</a>
                        <a class="dropdown-item" href="/theme?theme=cyborg">Cyborg</a>
                        <a class="dropdown-item" href="/theme?theme=darkly">Darkly</a>
                        <a class="dropdown-item" href="/theme?theme=flatly">Flatly</a>
                        <a class="dropdown-item" href="/theme?theme=journal">Journal</a>
                        <a class="dropdown-item" href="/theme?theme=litera">Litera</a>
                        <a class="dropdown-item" href="/theme?theme=lumen">Lumen</a>
                        <a class="dropdown-item" href="/theme?theme=lux">Lux</a>
                        <a class="dropdown-item" href="/theme?theme=materia">Materia</a>
                        <a class="dropdown-item" href="/theme?theme=minty">Minty</a>
                        <a class="dropdown-item" href="/theme?theme=pulse">Pulse</a>
                        <a class="dropdown-item" href="/theme?theme=sandstone">Sandstone</a>
                        <a class="dropdown-item" href="/theme?theme=simplex">Simplex</a>
                        <a class="dropdown-item" href="/theme?theme=solar">Solar</a>
                        <a class="dropdown-item" href="/theme?theme=slate">Slate</a>
                        <a class="dropdown-item" href="/theme?theme=sketchy">Sketchy</a>
                        <a class="dropdown-item" href="/theme?theme=spacelab">Spacelab</a>
                        <a class="dropdown-item" href="/theme?theme=superhero">Superhero</a>
                        <a class="dropdown-item" href="/theme?theme=united">United</a>
                        <%--<a class="dropdown-item" href="/theme?theme=yeti">Yeti</a>--%>
                    </div>
                </li>
                <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="../help/">Help</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="http://blog.bootswatch.com">Blog</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item dropdown">--%>
                    <%--<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="download">Solar <span class="caret"></span></a>--%>
                    <%--<div class="dropdown-menu" aria-labelledby="download">--%>
                        <%--<a class="dropdown-item" href="https://jsfiddle.net/bootswatch/mqoc3ah6/">Open in JSFiddle</a>--%>
                        <%--<div class="dropdown-divider"></div>--%>
                        <%--<a class="dropdown-item" href="../4/solar/bootstrap.min.css">bootstrap.min.css</a>--%>
                        <%--<a class="dropdown-item" href="../4/solar/bootstrap.css">bootstrap.css</a>--%>
                        <%--<div class="dropdown-divider"></div>--%>
                        <%--<a class="dropdown-item" href="../4/solar/_variables.scss">_variables.scss</a>--%>
                        <%--<a class="dropdown-item" href="../4/solar/_bootswatch.scss">_bootswatch.scss</a>--%>
                    <%--</div>--%>
                <%--</li>--%>
            </ul>

            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item user-logged-in">
                    <a class="nav-link" href="/login"><i class="fas fa-sign-in-alt"></i> Login</a>
                </li>
                <li class="nav-item user-logged-in mr-1">
                    <a class="nav-link" href="/register"><i class="fas fa-user"></i> Register</a>
                </li>
                <li class="nav-item not-logged-in">
                    <a class="nav-link" href="/profile"><i class="fas fa-user-circle"></i> Profile</a>
                </li>
                <li class="nav-item not-logged-in mr-1">
                    <a class="nav-link" href="/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </li>
                <li class="nav-item d-none" id="searchNavItem">
                    <input class="form-control" type="search" placeholder="Search" aria-label="Search" id="navbarSearch" readonly>
                </li>
            </ul>

        </div>
    </div>
</div>

<span class="material-icons position-absolute d-md-none" id="searchIcon">search</span>

<jsp:include page="/WEB-INF/assests/search.jsp" />
