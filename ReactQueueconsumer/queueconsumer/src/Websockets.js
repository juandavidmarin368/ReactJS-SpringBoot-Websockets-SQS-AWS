import React, { Component } from "react";
import SockJsClient from 'react-stomp';
import Typography from '@material-ui/core/Typography';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import LinearProgress from '@material-ui/core/LinearProgress';
import CircularProgress from '@material-ui/core/CircularProgress';

// or





const TableProductos = props => {


    return (
        <tr className="select-row" >
            <th scope="row"></th>
            <td>
                data
            </td>

            <td className="text-right">
                data
            </td>


        </tr>
    );
};




class Websockets extends React.Component {


    constructor(props) {
        super();
        this.state = {
            dataProvider: [],
            status: false,
            statusLoader: false,
            id: 0,
            nombre: ""

        };
        this.deleteFIFO = this.deleteFIFO.bind(this);

    }


    deleteFIFO() {



        fetch('http://localhost:8445/api/deletefifoitem', {
            method: 'post'
        }).then(function (response) {


        }).then(function (data) {

        });
        this.setState({ "statusLoader": true });

        setTimeout(
            function () {
                this.setState({ "status": false });
                this.setState({ "statusLoader": false });
            }
                .bind(this),
            8000
        );


    }

    changing


    render() {

        var self = this;
        return (
            <div>

                <br></br>

                {

                    this.state.status === true ? (

                        <Grid container spacing={3}>
                            <Grid item xs={12}>
                                <Card className="card">
                                    <CardContent>

                                        <div style={{ display: 'flex', justifyContent: 'center' }}>

                                            <p><strong>ID: {this.state.id}</strong></p>

                                        </div>

                                        <div style={{ display: 'flex', justifyContent: 'center' }}>
                                            <p><strong>NOMBRE: {this.state.nombre}</strong></p>
                                        </div>
                                    </CardContent>
                                </Card>
                            </Grid>

                            {
                                this.state.statusLoader === false ? (
                                    <Grid item xs={12}>
                                        <Card className="card">
                                            <CardContent>

                                                <div style={{ display: 'flex', justifyContent: 'center' }}>

                                                    <Button variant="outlined" size="large" color="primary" onClick={this.deleteFIFO}>ELIMINAR FIFO</Button><br></br>

                                                </div>


                                            </CardContent>

                                        </Card>
                                    </Grid>
                                ) : ""
                            }

                            {
                                this.state.statusLoader === true ? (

                                    <Grid item xs={12}>
                                        <Card className="card">
                                            <div style={{ display: 'flex', justifyContent: 'center' }}>
                                                <CircularProgress />
                                            </div>
                                        </Card>
                                    </Grid>

                                ) : ""

                            }


                        </Grid>



                    ) : (

                            <Grid container spacing={3}>
                                <Grid item xs={12}>
                                    <Card className="card">
                                        <CardContent>

                                            <div style={{ display: 'flex', justifyContent: 'center' }}>

                                                <p><strong>ESPERANDO POR ELEMENTOS</strong></p>

                                            </div>
                                            <div style={{ display: 'flex', justifyContent: 'center' }}>
                                                <CircularProgress />
                                            </div>

                                        </CardContent>
                                    </Card>
                                </Grid>
                            </Grid>


                        )

                }



                <SockJsClient url='http://localhost:8445/gs-guide-websocket' topics={['/topic/notification']}
                    onMessage={(msg) => {

                        if (Object.keys(self.state.dataProvider).length > 0) {


                            if (self.state.dataProvider.id !== msg.id) {

                                //console.log("THEY ARE SUPPOSED TO BE DIFFERENT..");
                                self.setState({ "dataProvider": msg, "status": true, "id": msg.id, "nombre": msg.nombre });


                            }

                        } else {

                            //console.log("THEY ARE BIGGER");
                            self.setState({ "dataProvider": msg, "status": true, "id": msg.id, "nombre": msg.nombre });


                        }
                        //

                    }}


                />

            </div>
        );
    }
}

export default Websockets;