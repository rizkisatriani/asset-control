<body class="theme-cyan font-montserrat light_version">

<div class="auth-main particles_js"> 
        <div class="auth_div vivify popIn">
        <div class="auth_brand">
            <a class="navbar-brand" href="javascript:void(0);"><i class="fa fa-balance-scale"></i> Stock Take</a>
        </div>
        <div class="card">
            <div class="body">
                <p class="lead">Login</p>
                <form class="form-auth-small m-t-20" method='post' action="<?php echo site_url(); ?>/Site/login">
                    <div class="form-group">
                        <label for="signin-email" class="control-label sr-only">Email</label>
                        <input type="number" name='nip' class="form-control round" id="signin-email"  placeholder="NIP">
                    </div>
                    <div class="form-group">
                        <label for="signin-password" class="control-label sr-only">Password</label>
                        <input type="password" name='password' class="form-control round" id="signin-password" value="thisisthepassword" placeholder="Password">
                    </div>
                    <div class="form-group clearfix">
                        <label class="fancy-checkbox element-left">
                            <input type="checkbox">
                            <span>Remember me</span>
                        </label>                
                    </div>
                    <button class="btn btn-primary btn-round btn-block"  >LOGIN</button>
                    <div class="bottom">
                        <span class="helper-text m-b-10"><i class="fa fa-lock"></i> <a href="page-forgot-password.html">Forgot password?</a></span>
                        <span>Don't have an account? <a href="page-register.html">Register</a></span>
                    </div>
                </form>
            </div>
        </div> 
    </div>
    <div id="particles-js"></div>
</div>  
</body>

<script>
 $(document).ready(function(){ 
 
   
 });
function Snackbar(pesan){
    var x = $("#snackbar");
    x.find('p').text(pesan);
  x.addClass('show');
  setTimeout(function(){ x.removeClass('show'); }, 3000);
}

function login(){

    Snackbar("Password Salah");
    email=$('#signin-email').val();
    password=$('#signin-password').val();
  $.ajax({
            url:"<?php echo site_url(); ?>/site/login",
             method:"POST",
             data:{nip:email,password:password},
             success:function(response){
              content=response;
             },
             complete:function(){  
              setTimeout(function(){ $('#content' ).html(content); }, 1000);
             },
             failed:function(){  
              Snackbar("Password Salah")
             }
             });
}
</script>

    <?php if($this->session->userdata("error_info")) echo "<script>Snackbar('".$this->session->userdata("error_info")."');</script>";

    $this->session->unset_userdata('error_info');?>