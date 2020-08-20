const toast = Swal.mixin({
   toast:true,
   position: 'top-end',
   showConfirmButton:false,
   timer:3000,
   timerProgressBar:true,
   onOpen:(toast)=>{
       toast.addEventListener('mouseenter',Swal.stopTimer)
       toast.addEventListener('mouseleave',Swal.stopTimer)
   }
});

// Toast.fire({
//    icon:'success',
//    title:'Signed in successfully'
// });

Toast = {
    success: function (message) {
        toast.fire({
            icon:'success',
            title:message
        });
    },

    error:function (message) {
        toast.fire({
            icon:'error',
            title:message
        });
    },

    warning:function (message) {
        toast.fire({
            icon:'warning',
            title:message
        });
    }


};
