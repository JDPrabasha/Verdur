export default class Manager_employee_serialize{
    static doSerialize(data){
        return{
            employeeid: data.employeeID,
            name: data.name,
            designation: data.role,
            address : data.Address,
            contact_no : data.contact_no,
            dob : data.dob,
            email : data.email,
            experience : data.experience,
            hiring_date : data.hiring_date,
            idno : data.idno,
            image : data.image,
            status : data.status
        }
    }
}