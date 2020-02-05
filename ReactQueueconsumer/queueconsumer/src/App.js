import React, { Component } from "react";
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';

import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Link from '@material-ui/core/Link';

import Websockets from './Websockets';

import BottomNavigation from '@material-ui/core/BottomNavigation';

import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';





class App extends Component {




  render() {

    const styles = theme => ({
      root: {
        ...theme.mixins.gutters(),
        paddingTop: theme.spacing.unit * 2,
        paddingBottom: theme.spacing.unit * 2,
      },
      footer: {

        backgroundColor: theme.palette.background.paper,
        marginTop: theme.spacing.unit * 8,
        padding: `${theme.spacing.unit * 6}px 0`,
      }
    });

    //const classes = useStyles();
    return (

      <div>
        <AppBar position="relative">
          <Toolbar>

            <Typography variant="h6" color="inherit" noWrap>
              AWS - SQS - WEBSOCKETS
          </Typography>
          </Toolbar>
        </AppBar>

        <Container maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={4}>



            <Grid item xs={12}>
              <br></br>
              <Websockets></Websockets>
            </Grid>


          </Grid>
        </Container>


      </div >

    );
  }
}

export default App;
