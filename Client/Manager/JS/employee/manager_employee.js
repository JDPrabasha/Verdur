import Manager_employee_serialize from "./manager_employee_serializer.js";

export default class Employee {

    constructor(params) {
        this.employeeid = params.employeeid;
        this.name = params.name;
        this.designation = params.designation;
        this.address = params.address;
        this.contact_no = params.contact_no;
        this.dob = params.dob;
        this.email = params.email;
        // this.experience = params.experience;
        this.experience = 2;
        this.hiring_date = params.hiring_date;
        this.idno = params.idno;
        this.image = params.image;
        this.status = params.status;
    }

    // addemployee(){

    //     // var employees = $("#employees");
    //     var employees =$("#results")
    //         var row = $(document.createElement('tr'))
    //         var image = $(document.createElement('td')).html(`<img class="icon1" src="Images/employee-icon.jpg">`);
    //         var employeeid = $(document.createElement('td')).html(this.employeeid);
    //         var name = $(document.createElement('td')).html(this.name);
    //         var des= $(document.createElement('td')).html(this.designation);
    //         var button = $(document.createElement('td')).html(`<a href="Manager-EmployeeView.html?eid=${this.employeeid}"><button>View</button></a>`);
    //         row.append(image);
    //         row.append(employeeid);
    //         row.append(name);
    //         row.append(des);
    //         row.append(button);
    //         employees.append(row);
    // }

    addemployeenew() {

        // var employees = $("#employees");
        var employees = $("#results")
        var row_nomrmal = $(document.createElement('tr')).attr("id", `rec-${this.employeeid}`);
        var col_1 = $(document.createElement('td')).html(`<img class="icon-3 icon emp-icon" src="${this.image}">`);
        var col_2 = $(document.createElement('td')).html(`<p class="emp_table_name">${this.name}</p>
                                                                <p class="emp_table_designation">${this.designation}</p>`);
        var col_3 = $(document.createElement('td')).html(`<p class="status_${this.status}">${this.status}</p>`);
        var col_4 = $(document.createElement('td')).html(`<div class="">${this.experience} Years</div>`);
        var col_5 = $(document.createElement('td')).html(` <p class="emp_table_email">${this.email}</p>
                                                                    <p class="emp_table_contact">${this.contact_no}</p>`);
        var col_6 = $(document.createElement('td')).html(`<p class="emp_table_address">${this.address}</p>`);
        // var col_7 = $(document.createElement('td')).html(`<button class="dropdown" onclick="expand(${this.employeeid})">...</button>`);
        // var col_7 = $(document.createElement('td')).html(`<div class="dropdown" onclick="expand(${this.employeeid})" style="padding:0;padding-right:4px;"><i class="material-icons" style="height:20px;width:20px">arrow_drop_down</i></div>`).attr("style","width:6%;");
        var col_7 = $(document.createElement('td')).html(`<div class="dropdown" id="expand-${parseInt(this.employeeid)}" style="padding:0;padding-right:4px;"><i class="material-icons" style="height:20px;width:20px">arrow_drop_down</i></div>`).attr("style", "width:6%;");

        row_nomrmal.append(col_1);
        row_nomrmal.append(col_2);
        row_nomrmal.append(col_3);
        row_nomrmal.append(col_4);
        row_nomrmal.append(col_5);
        row_nomrmal.append(col_6);
        row_nomrmal.append(col_7);


        var row_expand = $(document.createElement('tr')).attr("id", `rec-e-${this.employeeid}`).attr("class", "hidden");
        var col_e_1 = $(document.createElement('td')).attr("colspan", "6").html(`
                <form class="form_reset emp_form">
                    <div class="row">
                        <!-- 1st set -->
                        <div class="col-4">
                            <div class="row">
                                <div class="col-4">
                                    <label>Name</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" value="${this.name}" id="name" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label>ID</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" value="${this.idno}" id="idno" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label>Date of Birth</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" value="${this.dob}" id="dob" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label>Experience</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" value="${this.experience} Years" id="xp" disabled>
                                </div>
                            </div>
                        </div>
                        <!-- 2nd set -->
                        <div class="col-4">
                            <div class="row">
                                <div class="col-4">
                                    <label>Hiring Date</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" value="${this.hiring_date}" id="hire_date" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label>Role</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" value="${this.designation}" id="role" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label>Status</label>
                                </div>
                                <div class="col-8">
                                    <input type="text" class="status_${this.status}" value="${this.status}" id="Status" disabled>
                                </div>
                            </div>
                        </div>
                        <!-- 3rd set -->
                        <div class="col-3">
                            <div class="row">
                                <div class="col-5">
                                    <label>Email</label>
                                </div>
                                <div class="col-7">
                                    <input type="text" value="${this.email}" id="email" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">
                                    <label>Contact_no</label>
                                </div>
                                <div class="col-7">
                                    <input type="text" value="${this.contact_no}" id="contact_no" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">
                                    <label>Address</label>
                                </div>
                                <div class="col-7">
                                    <input type="text" value="${this.address}" id="address" disabled>
                                    <input type="text" value="" id="address-2" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="col-1">
                            
                                <div id="edit-${parseInt(this.employeeid)}" style="display:inline-block"><i class="material-icons" style="font-size:18px;cursor:pointer">edit</i></div>
                                <div id="delete-emp-${parseInt(this.employeeid)}" style="display:inline-block;float:middle"><i class="material-icons" style="font-size:18px;cursor:pointer;color:red">delete</i></div>
                            
                                <div id="done-edit-${parseInt(this.employeeid)}" style="display:none"><i class="material-icons" style="font-size:18px;cursor:pointer">done</i></div>
                                <div id="cancel-edit-${parseInt(this.employeeid)}" style="display:none"><i class="material-icons" style="font-size:18px;cursor:pointer">cancel</i></div>
                                
                        </div>
                    </div>
                </form>
                `);

        // var col_e_2 = $(document.createElement('td')).attr("style","width:1%;").html(`<div class="dropdown" onclick="hide(${this.employeeid})" style="padding:0;padding-right:4px;"><i class="material-icons" style="height:20px;width:20px">arrow_drop_up</i></div>`);
        var col_e_2 = $(document.createElement('td')).attr("style", "width:1%;").html(`<div class="dropdown" id="hide-${parseInt(this.employeeid)}" style="padding:0;padding-right:4px;cursor:pointer"><i class="material-icons" style="height:20px;width:20px">arrow_drop_up</i></div>`);

        row_expand.append(col_e_1);
        row_expand.append(col_e_2);





        employees.append(row_nomrmal);
        employees.append(row_expand);

    }


    //change page function
    static changepage(n,array,chunk) {
        $("#results").html('');
        const deSerializedData = array.slice(n * chunk - chunk, n * chunk).map(i => Manager_employee_serialize.doSerialize(i));
        deSerializedData.map(params => new Employee(params).addemployeenew());
    }

}