class Profile {
  constructor(params) {
    this.id = params.id;
    this.name = params.name;
    this.avatar = params.avatar;
    this.contact = params.contact;
    this.address = params.address;
    this.gender = params.gender;
    this.email = params.email;
  }

  updateNavbar() {
    $("#customer").html(this.name);
    $("#avatar").attr("src", this.avatar);
  }
  addProfileDetails() {
    $("#customer").html(this.name);
    $("#avatar").attr("src", this.avatar);
    $("#gender").attr("src", this.gender);
    $("#address").attr("src", this.address);
    $("#email").attr("src", this.email);
  }

  addCustomerDetails() {
    $("#customer").html(this.name);
    $("#contact").html(this.contact);
  }
}
