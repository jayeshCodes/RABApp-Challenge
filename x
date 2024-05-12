<div >
  <router-outlet></router-outlet>
</div>
<div class="main-container">
  <div class="navbarcontainer">
    <div class="collapse" id="navbarToggleExternalContent">
      <div class="bg-dark p-4"></div>
    </div>
    <nav class="navbar navbar-dark bg-dark">
      <div class="container-fluid">
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#sidebar"
          aria-controls="navbarToggleExternalContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <h4 style="color: white;margin: 0;padding: 0;">RAB App</h4>
      </div>
    </nav>
  </div>
  <div class="sidebar-container">
    <div
      class="offcanvas offcanvas-start"
      tabindex="-1"
      id="sidebar"
      aria-labelledby="offcanvasLabel"
    >
      <div class="offcanvas-header">
        <h4 class="offcanvas-title" id="offcanvasLabel">Tracker</h4>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="offcanvas"
          aria-label="Close"
        ></button>
      </div>
      <div class="offcanvas-body">
        <div class="deleteButton">
          <div class="custom-button">
            <h5>Farm Data</h5>
          </div>
        </div>
        <div class="deleteButton">
          <div class="custom-button">
            <h5>Movement Data</h5>
          </div>
        </div>        
      </div>
    </div>
  </div>

  <div class="content-container">
    <router-outlet></router-outlet>
  </div>
</div>
