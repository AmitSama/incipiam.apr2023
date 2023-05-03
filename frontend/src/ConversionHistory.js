import PropTypes from 'prop-types';
import './ConversionHistory.css';

function ConversionHistory(props) {
    return (
        <div className="history">
            {props.history.map((hist => (
                <li>{hist}</li>
            )))}
        </div>
    );
}

ConversionHistory.propTypes = {
    history: PropTypes.array,
};

export default ConversionHistory;