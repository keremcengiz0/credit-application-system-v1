import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import {
  TextField,
  Button,
  Stepper,
  Step,
  StepLabel,
  Snackbar,
} from "@material-ui/core";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@mui/material/Typography";
import MuiAlert from "@material-ui/lab/Alert";
import CircularProgress from "@mui/material/CircularProgress";

const useStyles = makeStyles((theme) => ({
  textfield: { marginTop: 20 },
  alertStyle: {
    position: "fixed",
    bottom: "50%",
    left: "50%",
    transform: "translate(-50%, 50%)",
    width: "400px",
    zIndex: 9999,
  },
}));

const UpdateCustomer = () => {
  let navigate = useNavigate();
  const classes = useStyles();
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertType, setAlertType] = useState("success");
  const [alertMessage, setAlertMessage] = useState("");
  const [progress, setProgress] = useState(false);

  const [customer, setCustomer] = useState({
    identityNumber: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    birthDate: "",
  });
  const { id } = useParams();

  useEffect(() => {
    const getCustomer = async () => {
      const response = await axios.get(`/customers/get/${id}`);
      setCustomer(response.data);
    };
    getCustomer();
  }, [id]);

  const handleChange = (event) => {
    setCustomer({ ...customer, [event.target.name]: event.target.value });
  };

  const handleUpdate = async () => {
    setProgress(true);
    try {
      await axios.put(`/customers/${id}`, customer);
      setAlertType("success");
      setAlertMessage("The customer has been successfully updated");
      setAlertOpen(true);
      setProgress(false);
      setTimeout(() => {
        navigate("/customers");
      }, 2000);
    } catch (error) {
      setAlertType("error");
      setAlertMessage("The customer could not be updated");
      setAlertOpen(true);
      setProgress(false);
    }
  };

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setAlertOpen(false);
  };

  return (
    <div>
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
      <Stepper activeStep={0}>
        <Step>
          <StepLabel>Update a Customer</StepLabel>
        </Step>
      </Stepper>
      <Typography variant="h5">
        <b>Update a Customer</b>
      </Typography>
      <TextField
        disabled
        id="identityNumber"
        label="Identity Number"
        type="number"
        value={customer.identityNumber}
        onChange={handleChange}
        className={classes.textfield}
      />
      <TextField
        label="First Name"
        name="firstName"
        value={customer.firstName}
        onChange={handleChange}
        className={classes.textfield}
      />
      <TextField
        label="Last Name"
        name="lastName"
        value={customer.lastName}
        onChange={handleChange}
        className={classes.textfield}
      />
      <TextField
        label="Phone Number"
        name="phoneNumber"
        value={customer.phoneNumber}
        onChange={handleChange}
        className={classes.textfield}
      />
      <TextField
        label="Birth Date"
        name="birthDate"
        type="date"
        value={customer.birthDate}
        onChange={handleChange}
        InputLabelProps={{ shrink: true }}
        className={classes.textfield}
      />
      <Button
        style={{ marginLeft: -100, marginTop: 100 }}
        onClick={handleUpdate}
      >
        <b>Update</b>
      </Button>

      <Snackbar
        open={alertOpen}
        autoHideDuration={2000}
        onClose={handleCloseAlert}
      >
        <MuiAlert
          elevation={6}
          variant="filled"
          onClose={handleCloseAlert}
          severity={alertType}
        >
          {alertMessage}
        </MuiAlert>
      </Snackbar>
    </div>
  );
};
export default UpdateCustomer;
