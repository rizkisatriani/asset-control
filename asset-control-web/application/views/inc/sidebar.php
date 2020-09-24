 <div id="left-sidebar" class="sidebar">
        <div class="navbar-brand">
            <a href="<?php echo site_url()?>/home"><!-- <img src="#" alt="Oculux Logo" class="img-fluid logo"> --><i class="fa fa-balance-scale"></i><span>Stock Take</span></a>
            <button type="button" class="btn-toggle-offcanvas btn btn-sm float-right"><i class="lnr lnr-menu icon-close"></i></button>
        </div>
        <div class="sidebar-scroll">
            <div class="user-account">
                <div class="user_div">
                   <!--  <img src=".#" class="user-photo" alt="User Profile Picture"> -->
                   <i class="fa fa-user-circle" aria-hidden="true"></i>
                </div>
                <div class="dropdown">
                    <span>Selamat Datang,</span>
                    <a href="javascript:void(0);" class="dropdown-toggle user-name" data-toggle="dropdown"><strong>Users</strong></a>
                    <ul class="dropdown-menu dropdown-menu-right account vivify flipInY">
                        <li><a href="page-profile.html"><i class="icon-user"></i>My Profile</a></li>
                        <li><a href="app-inbox.html"><i class="icon-envelope-open"></i>Messages</a></li>
                        <li><a href="javascript:void(0);"><i class="icon-settings"></i>Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="page-login.html"><i class="icon-power"></i>Logout</a></li>
                    </ul>
                </div>                
            </div>  
            <nav id="left-sidebar-nav" class="sidebar-nav">
                <ul id="main-menu" class="metismenu">
                    <li class="header">Main</li>
                    <li class="active open">
                        <a href="<?php echo site_url()?>/home" class="has-arrow"><i class="icon-home"></i><span>Home</span></a>
                        <ul>
                            <li class="active"><a href="<?php echo site_url()?>/home">Dashboard</a></li> 
                        </ul>
                    </li>
                    <li class="metismenu"><a href="#myPage" class="has-arrow"><i class="icon-cloud-upload"></i><span>Upload</span></a>
                        <ul>
                            <li class="active"><a href="<?php echo site_url()?>/upload/indexupload">Upload Data Asset</a></li> 
                            <li class="active"><a href="<?php echo site_url()?>/list-assets/0">Datar Data Upload </a></li> 
                        </ul>
                    </li>
                    <li class="metismenu"><a href="#myPage" class="has-arrow"><i class="icon-settings"></i><span>Setting Master</span></a>
                        <ul>
                            <li class="active"><a href="<?php echo site_url()?>/list-user">Daftar Pengguna</a></li>  
                        </ul>
                    </li>    
                </ul>
            </nav>     
        </div>
    </div>