<div id="main-content"> 
        <div class="container-fluid">
            <div class="block-header">
                <div class="row clearfix">
                    <div class="col-md-6 col-sm-12">
                        <h1>Upload Data</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Upload</a></li> 
                            </ol>
                        </nav>
                    </div>            
                  <!--   <div class="col-md-6 col-sm-12 text-right hidden-xs">
                        <a href="javascript:void(0);" class="btn btn-sm btn-primary" title="">Download</a>
                        <a href="https://themeforest.net/item/oculux-bootstrap-4x-admin-dashboard-clean-modern-ui-kit/23091507" class="btn btn-sm btn-success" title="Themeforest"><i class="icon-printer"></i> Print</a>
                    </div> -->
                </div>
            </div>
 
    <div id="content" class="col-sm-12 col-md-12 col-lg-12 mx-auto shimer"></div>
    </div> 

<div class="modal fade" id="addevent" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content text-center">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="form-line">
                <h3 class="title" id="defaultModalLabel">Upload Data Asset</h3>
                        <label>Apakah anda yakin ingin upload data ?</label>
                    </div>
                </div>      
            </div>
            <div class="modal-footer col-sm-12">
                <button type="button" id="upload" class="btn   btn-primary col-sm-6" data-dismiss="modal">Upload</button>
                <button type="button" class="btn  btn-danger col-sm-6" data-dismiss="modal">Tolak</button>
            </div>
        </div>
    </div>
</div>

 

</div>  
<script>


$(document).ready(function(){ 
   show_data();
});

function Snackbar(pesan){
    var x = $("#snackbar");
    x.find('p').text(pesan);
  x.addClass('show');
  setTimeout(function(){ x.removeClass('show'); }, 3000);
}

function show_data(){
  $.ajax({url:"<?php echo site_url(); ?>/Data_assets/fetch_header",
             method:"POST",
             success:function(response){
              content=response;
             },
             complete:function(){  
              setTimeout(function(){ $('#content' ).html(content); }, 1000);
             }
             });
}
</script>