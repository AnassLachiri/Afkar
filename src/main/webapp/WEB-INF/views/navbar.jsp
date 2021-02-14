<nav class="navbar navbar-expand-md navbar-light shadow-sm n">

    <a class="navbar-brand" href="#">
        <img class="logo" src="static_image?image=logo.png" alt="Afkar">
        <span>Afkar</span>
    </a>

    <ul class="navbar-nav ml-auto ">
        <form class="form-inline search-bar" action="/search" method="GET">
            <select name="category" class="category-filter custom-select">
                <option value="story">Story</option>
                <option value="user">User</option>
            </select>
            <input class="form-control" type="search" placeholder="Search" aria-label="Search" name="search_text">
            <button class="btn btn-outline-info" type="submit">
                <svg class="bi bi-search" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 011.415 0l3.85 3.85a1 1 0 01-1.414 1.415l-3.85-3.85a1 1 0 010-1.415z" clip-rule="evenodd"/>
                    <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 100-11 5.5 5.5 0 000 11zM13 6.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0z" clip-rule="evenodd"/>
                </svg>
            </button>
        </form>


        <li class="nav-item dropdown">
            <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                <img src="levi.jpg" class="profile-photo" alt="your profile"/><span class="caret"></span>
            </a>

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a href="#" class="dropdown-item">Mon Profile</a>
                <a href="#" class="dropdown-item">Mes Stories</a>
                <a href="#" class="dropdown-item">Ecrire une Story</a>
                <a href="#" class="dropdown-item">Déconnecter</a>
            </div>
        </li>
    </ul>

</nav>