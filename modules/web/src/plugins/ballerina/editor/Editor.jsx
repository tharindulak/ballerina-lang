import React from 'react';
import PropTypes from 'prop-types';
import BallerinaFileEditor from './../../../../js/ballerina/views/ballerina-file-editor';

/**
 * Editor for Bal Files
 */
class Editor extends React.Component {

    /**
     * @inheritdoc
     */
    render() {
        return (
            <div>
                <BallerinaFileEditor {...this.props} />
            </div>
        );
    }
}

Editor.propTypes = {
    file: PropTypes.objectOf(Object).isRequired,
    isActive: PropTypes.bool.isRequired,
    commandProxy: PropTypes.shape({
        on: PropTypes.func.isRequired,
        dispatch: PropTypes.func.isRequired,
        getCommands: PropTypes.func.isRequired,
    }).isRequired,
};

export default Editor;
