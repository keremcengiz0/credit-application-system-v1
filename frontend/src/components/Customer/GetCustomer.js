import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import Container from "@mui/material/Container";
import DeleteIcon from "@mui/icons-material/Delete";
import IconButton from "@mui/material/IconButton";
import CreateIcon from "@mui/icons-material/Create";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import useMediaQuery from "@mui/material/useMediaQuery";
import { useTheme } from "@mui/material/styles";
import Button from "@mui/material/Button";
import { TextField } from "@material-ui/core";
import CircularProgress from "@mui/material/CircularProgress";

const useStyles = makeStyles({
  table: {
    minWidth: 500,
  },
  tableTitle: {
    fontWeight: "bold",
  },
});

function GetCustomer() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);
  const [customers, setCustomers] = useState([]);
  const [customerId, setCustomerId] = useState(null);
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const theme = useTheme();
  const fullScreen = useMediaQuery(theme.breakpoints.down("md"));
  const [searchTerm, setSearchTerm] = useState("");
  const [progress, setProgress] = useState(false);

  const handleClickOpen = (id) => {
    setCustomerId(id);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setCustomerId(null);
  };

  const deleteCustomer = () => {
    axios
      .delete(`/customers/${customerId}`)
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
      .catch((error) => {
        console.error(error);
      });
  };

  useEffect(() => {
    setProgress(true);
    axios
      .get(`/customers`)
      .then(
        (response) => {
          setProgress(false);
          setIsLoaded(true);
          setCustomers(response.data);
        },
        (error) => {
          setProgress(false);
          setIsLoaded(true);
          setError(error);
        }
      )
      .catch((error) => console.log(error));
  }, []);

  return (
    <div style={{ display: "flex", justifyContent: "center" }}>
      {progress && (
        <div
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundColor: "rgba(0, 0, 0, 0.5)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <CircularProgress />
        </div>
      )}
      <Container maxWidth="lg">
        <br />
        <h2 style={{ textAlign: "center" }}>Customers</h2>
        <br />
        <br />
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TextField
                label="Search by Identity Number"
                variant="outlined"
                size="small"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                style={{ marginTop: 10 }}
              />
              <TableRow>
                <TableCell align="center" className={classes.tableTitle}>
                  Customer Id
                </TableCell>
                <TableCell align="center" className={classes.tableTitle}>
                  Identity Number
                </TableCell>
                <TableCell align="center" className={classes.tableTitle}>
                  Name
                </TableCell>
                <TableCell align="center" className={classes.tableTitle}>
                  Surname
                </TableCell>
                <TableCell align="center" className={classes.tableTitle}>
                  Phone Number
                </TableCell>
                <TableCell align="center" className={classes.tableTitle}>
                  Birth Date (yyyy-mm-dd)
                </TableCell>
                <TableCell align="center" className={classes.tableTitle}>
                  Actions
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {customers
                .filter((customer) => {
                  if (searchTerm === "") {
                    return true;
                  } else if (
                    customer.identityNumber
                      .toLowerCase()
                      .includes(searchTerm.toLowerCase())
                  ) {
                    return true;
                  }
                  return false;
                })
                .map((customer) => (
                  <TableRow key={customer.id}>
                    <TableCell align="center" component="th" scope="row">
                      {customer.id}
                    </TableCell>
                    <TableCell align="center">
                      {customer.identityNumber}
                    </TableCell>
                    <TableCell align="center">{customer.firstName}</TableCell>
                    <TableCell align="center">{customer.lastName}</TableCell>
                    <TableCell align="center">
                      (+90){customer.phoneNumber}
                    </TableCell>
                    <TableCell align="center">{customer.birthDate}</TableCell>
                    <TableCell align="center">
                      <IconButton onClick={() => handleClickOpen(customer.id)}>
                        <DeleteIcon />
                      </IconButton>
                      <Dialog
                        fullScreen={fullScreen}
                        open={open}
                        onClose={handleClose}
                        aria-labelledby="responsive-dialog-title"
                      >
                        <DialogContent>
                          <DialogContentText>
                            <b style={{ fontSize: 24 }}>
                              Are you sure you want to delete the customer?
                            </b>
                          </DialogContentText>
                        </DialogContent>
                        <DialogActions>
                          <Button autoFocus onClick={handleClose}>
                            <b>Cancel</b>
                          </Button>
                          <Button
                            onClick={() => {
                              handleClose();
                              deleteCustomer();
                            }}
                            autoFocus
                          >
                            <b style={{ color: "red" }}>Delete</b>
                          </Button>
                        </DialogActions>
                      </Dialog>
                      <IconButton
                        onClick={() => {
                          window.location = `/customers/update/${customer.id}`;
                        }}
                      >
                        <CreateIcon />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    </div>
  );
}

export default GetCustomer;
