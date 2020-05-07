package com.ratna.architecture.transferobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="employees")
public class Employees {
	
	private Employee[] employee;

    public Employee[] getEmployee ()
    {
        return employee;
    }

    public void setEmployee (Employee[] employee)
    {
        this.employee = employee;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [employee = "+employee+"]";
    }

}
