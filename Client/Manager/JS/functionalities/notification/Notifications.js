export class Notification{

    constructor(params){
        this.id = params.id;
        this.type = params.type;
        this.icon = this.type=="Restock"?"inventory":this.type=="Dish"?"lunch_dining":"error_outline";
        this.description = params.description;
        this.targetID = params.targetID
    }

    printNotification(){
        let notificationContainer = $(".notification-pop");
        // console.log(this.type)
        // console.log(this.type=="Dish")
        if(this.type=="Dish"){
            this.targetUrl = "/Client/Manager/Manager-Dishes.html?id="+this.targetID+"&type="
            let requestType = this.description.split(" ")[0].toLowerCase() ;
            if(requestType=="new"){
                this.targetUrl+="Add Dish"
            }else if(requestType=="remove" || requestType=="delete"){
                this.targetUrl+="Remove Dish"
            }else if(requestType=="update" || requestType=="updated"){
                this.targetUrl+="Update Dish"
            }
        }else if(this.type=="Restock"){
            this.targetUrl = "/Client/Manager/Manager-Restock.html";
        }

        let notification = $(document.createElement('div')).attr("class","notification-seperator").attr("id","notifID-"+this.id)
        notification.html(`
            <div>
                <span class="material-icons pr-1" style="vertical-align: middle;cursor:pointer;" onclick="window.location='${this.targetUrl}'">${this.icon}</span>
                <span style="vertical-align: middle;font-size: 16px;cursor:pointer;" onclick='window.location="${this.targetUrl}"'>${this.type}</span>
                <span id="remove-notif-${this.id}" class="material-icons" style ="position:absolute;right:18px;vertical-align:middle;font-size:18px;color:red;cursor:pointer">remove_done</span>
            </div>
            <div class="pl-7" onclick='window.location="${this.targetUrl}"' style="cursor:pointer">${this.description}</div>`)

        notificationContainer.append(notification);
    }
}