import React, { useState } from "react";
import {
  Stepper,
  Step,
  StepLabel,
  Button,
  Typography,
  TextField,
  Snackbar,
} from "@material-ui/core";
import Axios from "axios";
import { makeStyles } from "@material-ui/core/styles";
import { useNavigate } from "react-router-dom";
import MuiAlert from "@material-ui/lab/Alert";
import CircularProgress from "@mui/material/CircularProgress";

const useStyles = makeStyles((theme) => ({
  textfield: { marginTop: 20 },
}));

function CreateACustomerAndMakeAnApplicationForm() {
  const classes = useStyles();
  let navigate = useNavigate();
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertType, setAlertType] = useState("success");
  const [alertMessage, setAlertMessage] = useState("");
  const [identityNumberError, setIdentityNumberError] = useState(false);
  const [progress, setProgress] = useState(false);

  const [stateApplication, setStateApplication] = useState({
    identityNumber: "",
    salary: "",
    guarantee: "",
  });

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setAlertOpen(false);
  };

  const steps = ["Make an Application"];

  const handleChange = (event) => {
    const { name, value } = event.target;

    if (name === "identityNumber") {
      if (value.length !== 11) {
        setIdentityNumberError(true);
      } else {
        setIdentityNumberError(false);
      }
    }
    setStateApplication({ ...stateApplication, [name]: value });
  };

  const handleApplicationSubmit = async () => {
    setProgress(true);
    const applicationData = { ...stateApplication };
    try {
      const response = await Axios.post(
        `/applications/${applicationData.identityNumber}`,
        applicationData
      );
      if (response.status === 200) {
        setProgress(false);
        setAlertType("success");
        setAlertMessage("Application completed successfully");
        setAlertOpen(true);
        setTimeout(() => {
          navigate("/applications/get-status");
        }, 2000);
      }
    } catch (error) {
      setProgress(false);
      setAlertType("error");
      setAlertMessage("Application failed");
      setAlertOpen(true);
      console.error(error);
    }
  };

  return (
    <div>
      <br />
      <br />
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
      <div style={{ display: "flex", justifyContent: "center" }}>
        <Stepper
          style={{
            width: "80%",
            backgroundColor: "transparent",
            marginLeft: 100,
          }}
        >
          {steps.map((label) => (
            <Step key={label}>
              <StepLabel>{label}</StepLabel>
            </Step>
          ))}
        </Stepper>
      </div>

      <div>
        <div>
          <Typography style={{ marginLeft: -60 }} variant="h5">
            <b>Make an Application</b>
          </Typography>
          <TextField
            label="Identity Number"
            name="identityNumber"
            value={stateApplication.identityNumber}
            className={classes.textfield}
            onChange={handleChange}
            error={identityNumberError}
            helperText={
              identityNumberError ? "Identity Number must be 11 digits." : null
            }
            inputProps={{ maxLength: 11 }}
          />
          <TextField
            label="Salary"
            name="salary"
            value={stateApplication.salary}
            className={classes.textfield}
            onChange={(event) =>
              setStateApplication({
                ...stateApplication,
                salary: event.target.value,
              })
            }
          />
          <TextField
            label="Guarantee"
            name="guarantee"
            value={stateApplication.guarantee}
            className={classes.textfield}
            onChange={(event) =>
              setStateApplication({
                ...stateApplication,
                guarantee: event.target.value,
              })
            }
          />
          <Button
            style={{ marginLeft: 30, marginTop: 40 }}
            onClick={handleApplicationSubmit}
          >
            <b>APPLY</b>
          </Button>

          <Snackbar
            open={alertOpen}
            autoHideDuration={1500}
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
      </div>
    </div>
  );
}

export default CreateACustomerAndMakeAnApplicationForm;
