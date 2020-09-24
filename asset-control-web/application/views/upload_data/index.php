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
              
    <div id='content'>
      
      <div>
              <h4 class="card-title text-left">Unggah Data Asset</h4> 
               <p>Silahkan upload daftar data asset yang akan di stock take </p>
      </div>
   <div class="row" >

         

        <div class="col-sm-12 col-md-12 col-lg-12 mx-auto">
          <div class="skeleton  card card-upload my-2 ">
            <div class="card-body text-left">  
              <h6 class="card-title text-left">Form Data Asset</h6> 
                   <div class="ctrl">
                  <span class="nortification animateOpen">Add New Message!</span>
                  </div>
                
               <div class="form-group row ">
                <label for="staticEmail" class="col-sm-2 col-form-label">Tanggal Pelaksanaan</label>
                <div class="col-sm-10">
                  <div id="date-picker-example" class="md-form md-outline input-with-post-icon  " inline="true">
                     <input class="datepicker" id="tanggal" readonly>
                    <i class="fa fa-calendar  "></i>
                  </div>
                </div>
              </div>

              <div class="form-group row">
                      <label for="staticEmail" class="col-sm-2 col-form-label">Lokasi Pelaksanaan</label>
                <div class="col-sm-10">
                  <select id="lokasi" class="form-control form-control-sm">
                    <option>Pilih Lokasi</option>
                    <option>Divisi IT</option>
                    <option>Divisi Warehouse</option>
                    <option>Divisi Plantation</option>
                  </select>
                </div>
              </div>

              <div class="form-group row">

                      <label for="staticEmail" class="col-sm-2 col-form-label">User Stock Take</label>
                      <div class="col-sm-10">
                  <div class="dropdown">
                    <button onclick="show_dropdown()" class="btn btn-success">Tambah User</button>
                    <div id="myDropdown" class="dropdown-content">
                      <input type="text" placeholder="Search.." id="myInput" onkeyup="filterFunction()"> 
                    </div>
                  </div>
                      <br/>
                      <br/> 
                      <div id="list-user" class="text-left col-lg-12">
                      </div>
                </div>
              </div>


               <div class="form-group row">
                      <label for="staticEmail" class="col-sm-2 col-form-label">File Data Asset</label>
                      <div class="col-sm-10">
                      <input id="fileInput" type="file" style="display:none" required accept=".xls, .xlsx" />
                      <button class="btn btn-success my-2 my-sm-1" id="file" type="submit">Pilih File</button>  
                      <p style="color:#27ae60;">Pastikan file yang anda upload berekstensi *.xlsx .xls .csv</p> 
                      <div id="list" class="text-left">
                      </div>
                      </div>  
               </div>

              <button class="btn btn-primary my-2 my-sm-2" id="modalupload" type="submit">Upload</button>  
            </div>
          </div> 
      </div> 
    </div>
    </div>
      <div id="response" class="col-sm-12 col-md-12 col-lg-12 mx-auto"></div>
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
    var data_user=[];
  $(document).ready(function(){ 
    get_data_user();
    $('.ctrl').hide(); 
     $(function(){ 
    $(".datepicker").datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayHighlight: true,
    });
   });
  $('#response').hide();
    
  $('#file').click(function(){
    $('#fileInput').click();
  }) 

  $('#modalupload').click(function(){
    if($(".datepicker").val()==""){
      Snackbar("Anda belum menginput tanggal Pelaksanaan !!!");
      //alert("Anda belum menginput tanggal Pelaksanaan !!!");
    }else if($("#lokasi")[0].selectedIndex==0){ 
     Snackbar("Anda belum menginput lokasi pelaksanaan !!!");
    }else if(data_user.length==0){ 
       Snackbar("Anda belum menginput data user !!!");
    }else if($('#fileInput').val()==""){ 
       Snackbar("Anda belum menginput file asset !!!");
    }else{

    $('#addevent').modal();
    }
  })

  $('#upload').click(function(){ 
   // $('#addevent').modal();
        $('#response').show();
              var fileName = $('#fileInput').val();
              $('#list').append("<div class='progress'><div id='progress' class='progress-bar bg-success progress-bar-striped' style='width:00%'></div></div>");  
          var fd = new FormData();
          var files = $('#fileInput')[0].files[0];
          var tanggal,lokasi;
          tanggal=$('#tanggal').val();
          lokasi=$("#lokasi").val();
          fd.append('file',files);
          fd.append('lokasi',lokasi);
          fd.append('tanggal',tanggal);
          fd.append('data_user',JSON.stringify(data_user));
              $.ajax({
                xhr: function() {
          var xhr = new window.XMLHttpRequest();

          // Upload progress
          xhr.upload.addEventListener("progress", function(evt){
              if (evt.lengthComputable) {
                  var percentComplete =( evt.loaded / evt.total)*100;
                  $('#progress').css("width",percentComplete+'%');
                  console.log(percentComplete); 
              }
         }, false);

         // Download progress
         xhr.addEventListener("progress", function(evt){
             if (evt.lengthComputable) {
                 var percentComplete = (evt.loaded / evt.total)*100;
                 // Do something with download progress
                 console.log(percentComplete);
             }
         }, false); 

         return xhr;
      },
               url:"<?php echo site_url(); ?>/excel_import/import",
               method:"POST",
               data:fd,
               contentType:false,
               cache:false,
               processData:false,
               beforeSend: function(){

               },
               success:function(data){
                var json = JSON.parse(data);
                $('#file').val('');
                var content;
                if(json[0].status){

                console.log(data);
               $.ajax({url:"<?php echo site_url(); ?>/excel_import/fetch",
               method:"POST",
               data:{id:json[0].id_stock_take},
               success:function(response){
                content=response;
               },
               complete:function(){ 
                setTimeout(function(){ $('#content').hide(1000);$('#response' ).html(content); }, 3000);
               }
               });

                }

             }
              });
  })

    $('#fileInput').change(function(e){ 
              var fileName = e.target.files[0].name;
              $('#list').append(' <b>'+fileName+"</b>");  
          
          })
   
  });


  function Snackbar(pesan){
      var x = $("#snackbar");
      x.find('p').text(pesan);
    x.addClass('show');
    setTimeout(function(){ x.removeClass('show'); }, 3000);
  }

  function show_dropdown() {
  $("#myDropdown").toggleClass("show"); 
  }

  function filterFunction() {
  var input, filter, ul, li, a, i;
  input = $("#myInput").val();
  filter = input.toUpperCase();
  div = $("#myDropdown");
  a = div.find("a");
  for (i = 0; i < a.length; i++) {
    console.log(a[i].textContent);
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
  }
  function addUser(id,nama){ 
    $('#list-user').append("<button id=\""+id+"\" onclick='remove(\""+id+"\",\""+nama+"\")'   class='btn btn-outline-success col-lg-2' style='margin:5px;'>"+nama+"</button>"); 
    $('a[id='+id+']').remove();
    show_dropdown(); 
    data_user.push(id); 
    console.log(data_user);
  }

  function remove(id,nama){  
     $('#myDropdown').append("<a href='#' id=\""+id+"\" onclick='addUser(\""+id+"\",\""+nama+"\")'>"+nama+"</a>");
     $('button[id='+id+']').remove(); 
     var index = data_user.indexOf(id);
    data_user.splice(index, 1); 
    console.log(data_user);
  }

  function get_data_user(){
  $.ajax({url:"<?php echo site_url(); ?>/Data_user/fetch",
             method:"POST",
             success:function(response){
              var data,message,kode,obj;
              obj=JSON.parse(response);
              data=JSON.parse(obj.data);
              message=obj.message;
              kode=obj.kode;
              console.log(data);
              for(i=0;i< data.length;i++){
                 $('#myDropdown').append("<a href='#' id=\""+data[i].id_user+"\" onclick='addUser(\""+data[i].id_user+"\",\""+data[i].nama_lengkap+"\")'>"+data[i].nama_lengkap+"</a>");
              }
             },
             complete:function(){   
             }
             });
  }

  </script>