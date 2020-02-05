import React, { Component } from 'react';
//import TableHeader from './TableHeader';


const TableHeader = () => {

    return (

        <thead>
            <tr>
                <th>Name</th>
                <th>Job</th>
            </tr>
        </thead>

    );

}

const TableBody = props => {

    const rowsT = props.characterData.map((rowT, index) => {

        return (

            <tr key={index}>
                <td>{rowT.name}</td>
                <td>{rowT.job}</td>
                <td><button onClick={() => props.removeCharacter(index)}>Delete></button></td>
            </tr>

        );


    });
    return <tbody>{rowsT}</tbody>;
}

class Table extends Component {
    render() {
        const { characterData, removeCharacter } = this.props;
        return (
            <table>
                <TableHeader />
                <TableBody characterData={characterData}
                    removeCharacter={removeCharacter}

                />

            </table>
        );
    }
}

export default Table;