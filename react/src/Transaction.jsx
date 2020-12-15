import React from 'react';
import './App.css';

const Transaction = ({data}) => {
    const {dateTime, amount, fullDescription} = data;

    return (
        <tr>
            <td>{dateTime.year}/{dateTime.month}/{dateTime.day}</td>
            <td>{fullDescription}</td>
            <td>{amount}</td>
        </tr>
    );
};

export default Transaction;