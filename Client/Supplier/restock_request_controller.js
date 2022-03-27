let flag = 0;
$(document).ready(function restockrequestlist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    let id = window.localStorage.getItem("id")
    // console.log(authHeader)
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Supplier/RestockRequestServlet?id="+id ,
        beforeSend: function(xhr){
            xhr.setRequestHeader("authorization", authHeader);
        }
        
     },).fail(function (jqXHR, textStatus, errorThrown) {
        window.location.href = "/Client/Manager/Invalid Token.html"
    }).then(function(data){
        
         var array = $.parseJSON(data);
         console.log(array);
         const deserializeddata = array.map(i=>restockrequestserializer.doserializer(i));
         deserializeddata.map(params=>new restockrequest(params).printrestockrequest());
         $("#loading").trigger("loaded")
         $('[id^=timeTillExp-').each(function(){
             console.log(this)
             let time = $(this).attr("time"),
             timecont = $(this)
             setInterval(function(){
                 timecont.html(getTimeRemaining(time))
                 if(flag!=0){
                    timecont.attr("style","color:red;")
                }
             }, 1000);
         })
     })
});


function getTimeRemaining(x) {
    // console.log(new Date(x))
    // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
    let difference;
    if(new Date(x) > new Date()) {
        difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
        flag = 0;
    }
    else{
        difference = new Date(new Date() - new Date(x) + (new Date().getTimezoneOffset() * 60 * 1000))
        // console.log("difference = " + difference);
        flag = 1;
    }

    let months = difference.getMonth(), 
    days = difference.getDate(),
    hours = difference.getHours(),
    mins = difference.getMinutes(),
    secs = difference.getSeconds(),
    timeremain;
    if(months!=0){
        timeremain = months+"  "+" Months "+days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(days!=0){
        timeremain = days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(hours!=0){
        timeremain =  hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(mins!=0){
        timeremain = mins + " mins " + secs +" secs"  
    }else{
        timeremain =   secs +" secs"  
    }
    return timeremain;
}