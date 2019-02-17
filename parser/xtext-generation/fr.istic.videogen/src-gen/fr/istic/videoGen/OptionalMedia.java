/**
 * generated by Xtext 2.15.0
 */
package fr.istic.videoGen;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Optional Media</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.istic.videoGen.OptionalMedia#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see fr.istic.videoGen.VideoGenPackage#getOptionalMedia()
 * @model
 * @generated
 */
public interface OptionalMedia extends Media
{
  /**
   * Returns the value of the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' containment reference.
   * @see #setDescription(MediaDescription)
   * @see fr.istic.videoGen.VideoGenPackage#getOptionalMedia_Description()
   * @model containment="true"
   * @generated
   */
  MediaDescription getDescription();

  /**
   * Sets the value of the '{@link fr.istic.videoGen.OptionalMedia#getDescription <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' containment reference.
   * @see #getDescription()
   * @generated
   */
  void setDescription(MediaDescription value);

} // OptionalMedia
