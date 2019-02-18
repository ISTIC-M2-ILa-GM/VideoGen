/**
 * generated by Xtext 2.15.0
 */
package fr.istic.idm.videogen.generated.videoGen;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mandatory Media</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.istic.idm.videogen.generated.videoGen.MandatoryMedia#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see fr.istic.idm.videogen.generated.videoGen.VideoGenPackage#getMandatoryMedia()
 * @model
 * @generated
 */
public interface MandatoryMedia extends Media
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
   * @see fr.istic.idm.videogen.generated.videoGen.VideoGenPackage#getMandatoryMedia_Description()
   * @model containment="true"
   * @generated
   */
  MediaDescription getDescription();

  /**
   * Sets the value of the '{@link fr.istic.idm.videogen.generated.videoGen.MandatoryMedia#getDescription <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' containment reference.
   * @see #getDescription()
   * @generated
   */
  void setDescription(MediaDescription value);

} // MandatoryMedia