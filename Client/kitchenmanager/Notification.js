class Notification {
  constructor(params) {
    this.id = params.id;
    this.status = params.status;
    this.timestamp = params.timestamp;
  }

  addNotification() {
    //     <div class="notify_item">

    //     <div class="notify_info">
    //       <p>Alex commented on<span>Timeline Share</span></p>
    //       <span class="notify_time">10 minutes ago</span>
    //     </div>
    //   </div>
    var dropdown = $("#dropdown");
    var item = $(document.createElement("div"))
      .addClass("notify_item")
      .attr("id", this.id);
    var info = $(document.createElement("div")).addClass("notify_info");
    var description = $(document.createElement("span")).html(this.description);
  

    var time = $(document.createElement("span"))
      .addClass("notify_time")
      .html(this.timestamp);

    // description.append(status);
    info.append(description);

    info.append(time);
    item.append(info);
    dropdown.append(item);
  }
}
