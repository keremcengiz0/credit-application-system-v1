import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { makeStyles } from "@material-ui/core/styles";
import { Link } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  link: {
    fontSize: "16px",
    textDecoration: "none",
    boxShadow: "none",
    color: "white",
  },
  makeApplication: {
    paddingLeft: 50,
  },
  getStatus: {
    paddingLeft: 60,
  },
  customer: {
    paddingLeft: 65,
  },
}));

function Navbar() {
  const classes = useStyles();

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" style={{ backgroundColor: "#4691fb" }}>
        <Toolbar>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{
              mr: 2,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".2rem",
              color: "inherit",
              display: { xs: "none", sm: "block" },
            }}
          >
            <Link className={classes.link} to={{ pathname: "/" }}>
              Credit Application System
            </Link>
          </Typography>
          <Typography
            variant="h6"
            component="div"
            className={classes.makeApplication}
            sx={{
              fontFamily: "monospace",
              fontWeight: 700,
              color: "inherit",
            }}
          >
            <Link
              className={classes.link}
              to={{
                pathname: "/applications/create-customer-and-make-application",
              }}
            >
              Create a Customer And Make an Application
            </Link>
          </Typography>
          <Typography
            variant="h6"
            component="div"
            className={classes.getStatus}
            sx={{
              fontFamily: "monospace",
              fontWeight: 700,
              color: "inherit",
            }}
          >
            <Link
              className={classes.link}
              to={{ pathname: "/applications/make-application" }}
            >
              Make an Application
            </Link>
          </Typography>
          <Typography
            variant="h6"
            component="div"
            className={classes.customer}
            sx={{
              fontFamily: "monospace",
              fontWeight: 700,
              color: "inherit",
            }}
          >
            <Link
              className={classes.link}
              to={{ pathname: "/applications/get-status" }}
            >
              Get Status
            </Link>
          </Typography>
          <Typography
            variant="h6"
            component="div"
            className={classes.customer}
            sx={{
              fontFamily: "monospace",
              fontWeight: 700,
              color: "inherit",
            }}
          >
            <Link className={classes.link} to={{ pathname: "/customers" }}>
              Get Customer
            </Link>
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default Navbar;
